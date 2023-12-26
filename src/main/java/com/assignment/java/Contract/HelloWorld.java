package com.assignment.java.Contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.10.3.
 */
@SuppressWarnings("rawtypes")
public class HelloWorld extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b506040516106f13803806106f183398101604081905261002e91610054565b5f610039828261019c565b5050610257565b634e487b7160e01b5f52604160045260245ffd5b5f6020808385031215610065575f80fd5b82516001600160401b038082111561007b575f80fd5b818501915085601f83011261008e575f80fd5b8151818111156100a0576100a0610040565b604051601f8201601f19908116603f011681019083821181831017156100c8576100c8610040565b8160405282815288868487010111156100df575f80fd5b5f93505b8284101561010057848401860151818501870152928501926100e3565b5f86848301015280965050505050505092915050565b600181811c9082168061012a57607f821691505b60208210810361014857634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115610197575f81815260208120601f850160051c810160208610156101745750805b601f850160051c820191505b8181101561019357828155600101610180565b5050505b505050565b81516001600160401b038111156101b5576101b5610040565b6101c9816101c38454610116565b8461014e565b602080601f8311600181146101fc575f84156101e55750858301515b5f19600386901b1c1916600185901b178555610193565b5f85815260208120601f198616915b8281101561022a5788860151825594840194600190910190840161020b565b508582101561024757878501515f19600388901b60f8161c191681555b5050505050600190811b01905550565b61048d806102645f395ff3fe608060405234801561000f575f80fd5b5060043610610034575f3560e01c80633d7403a314610038578063e21f37ce1461004d575b5f80fd5b61004b6100463660046101e1565b61006b565b005b610055610142565b60405161006291906102cf565b60405180910390f35b5f808054610078906102e8565b80601f01602080910402602001604051908101604052809291908181526020018280546100a4906102e8565b80156100ef5780601f106100c6576101008083540402835291602001916100ef565b820191905f5260205f20905b8154815290600101906020018083116100d257829003601f168201915b50505050509050815f9081610104919061036e565b507fcb85bf7f2ecdbde56164032ff593313842ea00f0afc05fe919fc979a7d00be50818360405161013692919061042a565b60405180910390a15050565b5f805461014e906102e8565b80601f016020809104026020016040519081016040528092919081815260200182805461017a906102e8565b80156101c55780601f1061019c576101008083540402835291602001916101c5565b820191905f5260205f20905b8154815290600101906020018083116101a857829003601f168201915b505050505081565b634e487b7160e01b5f52604160045260245ffd5b5f602082840312156101f1575f80fd5b813567ffffffffffffffff80821115610208575f80fd5b818401915084601f83011261021b575f80fd5b81358181111561022d5761022d6101cd565b604051601f8201601f19908116603f01168101908382118183101715610255576102556101cd565b8160405282815287602084870101111561026d575f80fd5b826020860160208301375f928101602001929092525095945050505050565b5f81518084525f5b818110156102b057602081850181015186830182015201610294565b505f602082860101526020601f19601f83011685010191505092915050565b602081525f6102e1602083018461028c565b9392505050565b600181811c908216806102fc57607f821691505b60208210810361031a57634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115610369575f81815260208120601f850160051c810160208610156103465750805b601f850160051c820191505b8181101561036557828155600101610352565b5050505b505050565b815167ffffffffffffffff811115610388576103886101cd565b61039c8161039684546102e8565b84610320565b602080601f8311600181146103cf575f84156103b85750858301515b5f19600386901b1c1916600185901b178555610365565b5f85815260208120601f198616915b828110156103fd578886015182559484019460019091019084016103de565b508582101561041a57878501515f19600388901b60f8161c191681555b5050505050600190811b01905550565b604081525f61043c604083018561028c565b828103602084015261044e818561028c565b9594505050505056fea264697066735822122013872c7b9160a27cd31829a74a6e9e7e3d5c671f1d38bcb670e170f2f169ab7764736f6c63430008140033";

    public static final String FUNC_MESSAGE = "message";

    public static final String FUNC_UPDATE = "update";

    public static final Event UPDATEDMESSAGE_EVENT = new Event("UpdatedMessage", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected HelloWorld(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected HelloWorld(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected HelloWorld(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected HelloWorld(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<UpdatedMessageEventResponse> getUpdatedMessageEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(UPDATEDMESSAGE_EVENT, transactionReceipt);
        ArrayList<UpdatedMessageEventResponse> responses = new ArrayList<UpdatedMessageEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            UpdatedMessageEventResponse typedResponse = new UpdatedMessageEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldStr = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.newStr = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static UpdatedMessageEventResponse getUpdatedMessageEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(UPDATEDMESSAGE_EVENT, log);
        UpdatedMessageEventResponse typedResponse = new UpdatedMessageEventResponse();
        typedResponse.log = log;
        typedResponse.oldStr = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.newStr = (String) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<UpdatedMessageEventResponse> updatedMessageEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getUpdatedMessageEventFromLog(log));
    }

    public Flowable<UpdatedMessageEventResponse> updatedMessageEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(UPDATEDMESSAGE_EVENT));
        return updatedMessageEventFlowable(filter);
    }

    public RemoteFunctionCall<String> message() {
        final Function function = new Function(FUNC_MESSAGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> update(String newMessage) {
        final Function function = new Function(
                FUNC_UPDATE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(newMessage)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static HelloWorld load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorld(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static HelloWorld load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorld(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static HelloWorld load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new HelloWorld(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static HelloWorld load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new HelloWorld(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<HelloWorld> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String initMessage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(initMessage)));
        return deployRemoteCall(HelloWorld.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<HelloWorld> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String initMessage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(initMessage)));
        return deployRemoteCall(HelloWorld.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<HelloWorld> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String initMessage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(initMessage)));
        return deployRemoteCall(HelloWorld.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<HelloWorld> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String initMessage) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(initMessage)));
        return deployRemoteCall(HelloWorld.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class UpdatedMessageEventResponse extends BaseEventResponse {
        public String oldStr;

        public String newStr;
    }
}
