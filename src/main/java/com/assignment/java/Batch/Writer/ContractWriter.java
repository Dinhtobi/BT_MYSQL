package com.assignment.java.Batch.Writer;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import com.assignment.java.DTO.Model.PostDTO;
import com.assignment.java.Entities.Post;
import com.assignment.java.Exception.NotFoundException;
import com.assignment.java.Repository.PostRepository;

public class ContractWriter implements ItemWriter<PostDTO>{

	private final PostRepository postRepository;
	
	
	public ContractWriter(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}


	@Override
	public void write(Chunk<? extends PostDTO> chunk) throws Exception {
		for(PostDTO postDTO : chunk) {
			if(postDTO.getTypeEvent().contains("CREATED")) {
				Post post = new Post();
				post.setId(postDTO.getId());
				post.setTitle(postDTO.getTitle());
				post.setDescription(postDTO.getDescription());
				post.setCreatedAt(postDTO.getCreatedAt());
				postRepository.save(post);
			}else if(postDTO.getTypeEvent().contains("UPDATED")) {
				Post post = postRepository.findById(postDTO.getId()).orElseThrow(() -> new NotFoundException("Post not found"));
				post.setTitle(postDTO.getTitle());
				post.setDescription(postDTO.getDescription());
				postRepository.save(post);
			}else if(postDTO.getTypeEvent().contains("DELETED")) {
				postRepository.deleteById(postDTO.getId());
			}else if(postDTO.getTypeEvent().contains("RESTORED")) {
				Post post = new Post();
				post.setId(postDTO.getId());
				post.setTitle(postDTO.getTitle());
				post.setDescription(postDTO.getDescription());
				post.setCreatedAt(postDTO.getCreatedAt());
				postRepository.save(post);
			}
			
		}
	}

	
}
