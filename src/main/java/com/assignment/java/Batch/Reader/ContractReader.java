package com.assignment.java.Batch.Reader;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.abi.EventEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.tx.gas.StaticGasProvider;

import com.assignment.java.Contract.Message;
import com.assignment.java.Contract.Message.ActionEventResponse;
import com.assignment.java.DTO.Model.PostDTO;
import com.assignment.java.Utils.AppConstants;
import com.assignment.java.Utils.ContractUtil;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContractReader implements ItemReader<PostDTO> {

	private final ContractUtil contractUtil;
	
	public ContractReader(ContractUtil contractUtil) {
		super();
		this.contractUtil = contractUtil;
	}
	
	private EthBlock.Block latestBlock = null ;
	private Disposable subscription;
	private List<PostDTO> list = new ArrayList<PostDTO>();
	private int index =0 ;
	@BeforeRead
	public void ReadBlock() throws IOException {
		Web3j web3j = contractUtil.webSocketService();
		BigInteger gasPrice = BigInteger.valueOf(200_000L);
		BigInteger gasLimit = BigInteger.valueOf(8_000_000);
		StaticGasProvider gasProvider = new StaticGasProvider(gasPrice, gasLimit);
//		if(!AppConstants.addressContract.equals("")) {
		Message message = Message.load("0xab13d8e6e1a0b94e3eef812e55adc3214e64fd71",web3j, contractUtil.credentials(), gasProvider);
		if(latestBlock == null) {
			latestBlock = web3j.ethGetBlockByNumber(DefaultBlockParameterName.EARLIEST, false).send().getBlock();
		}
		
		EthFilter filter = new EthFilter(
					  DefaultBlockParameter.valueOf(latestBlock.getNumber()),
				      DefaultBlockParameterName.LATEST,
				      message.getContractAddress()
			);
		
		if (subscription != null && !subscription.isDisposed()) {
	        subscription.dispose();
	    }
			filter.addSingleTopic(EventEncoder.encode(message.ACTION_EVENT));
			Flowable<ActionEventResponse> flowable = message.actionEventFlowable(filter);
			subscription = flowable.subscribe(response -> {
				PostDTO post = new PostDTO();
				post.setId(response.post.postId.intValue());
				post.setTitle(response.post.title);
				post.setDescription(response.post.description);
				post.setCreatedAt(new Date(response.post.created.longValue() + 1000));
				post.setUpdatedAt(new Date(response.post.updated.longValue() + 1000));
				post.setTypeEvent(response.actionType);
				log.info("block with id = {} " ,response.postId);
					list.add(post);
			});
			
		latestBlock = web3j.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send().getBlock();
//		}
			
	}
	
	@Override
	public PostDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if(index < list.size()) {
			PostDTO post = list.get(index);
			index++;
			return post;
		}else {
			index = 0 ;
			list.clear();
			return null ;
		}
		
	}

	
}
