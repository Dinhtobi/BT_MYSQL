// SPDX-License-Identifier: MIT
pragma solidity >= 0.8.20;

contract Message{
    
  

    address public owner;
    uint256 public activePostCounter =0 ; // sum activePostCounter
    uint256 public inactivePostCounter = 0; //sum inactivePosts
    uint256 private postCounter =0; // sum post

    enum Deactivated {NO ,YES}

    mapping(uint256 => address) public authorOf; // thong tin tac gia 

    mapping (uint256=>address) public delPostOf; // thong tin tac gia xoa 

    mapping(address => uint256) public postOf; // kiem tra bai post cua ai 

    struct PostStruct{
        uint256 postId;
        string title;
        string description;
        address author;
        Deactivated deleted;
        uint256 created;
        uint256 updated;        
    }

    PostStruct[] activePosts;
    PostStruct[] inactivePosts;


    event Action(
        uint256 postId,
        string actionType,
        PostStruct post,
        Deactivated deleted,
        address indexed executor,
        uint256 created
    );

    modifier ownerOnly(){
        require(msg.sender == owner, "Owner reserved only");
        _;
    }

    constructor(){
        owner = msg.sender;
    }

    function createPost(
        string memory title,
        string memory description
    ) external returns (bool){
        require(bytes(title).length > 0 , "Title cannot be empty");
        require(bytes(description).length > 0 , "Description cannot be empty");

        postCounter++;
        authorOf[postCounter] = msg.sender;
        postOf[msg.sender]++ ;
        activePostCounter++;
        PostStruct memory post = PostStruct(
                postCounter,
                title,
                description,
                msg.sender,
                Deactivated.NO,
                block.timestamp,
                block.timestamp
            );
        activePosts.push(
            post
        );
        emit Action(
            postCounter,
            "POST CREATED",
            post,
            Deactivated.NO,
            msg.sender,
            block.timestamp
        );
        return true;
    }

    function updatePost(
        uint256 postId,
        string memory title,
        string memory description 
    ) external returns(bool){
        require(authorOf[postId] == msg.sender, "Unauthorized entity");
        require(bytes(title).length > 0 , "Title cannot be empty");
        require(bytes(description).length >0 ,"Description cannot be empty");
        PostStruct memory post ;
        for(uint i =0 ; i< activePosts.length; i++){
            if(activePosts[i].postId == postId){
                activePosts[i].title = title;
                activePosts[i].description = description;
                activePosts[i].updated = block.timestamp;
                post = activePosts[i];
            }
        }

        emit Action(
            postId,
            "POST UPDATED",
            post,
            Deactivated.NO,
            msg.sender,
            block.timestamp
        );

        return true;
    }

    function showPost(
        uint256 postId
    ) external view returns (PostStruct memory){
        PostStruct memory post;

        for(uint i =0 ; i < activePosts.length; i++){
            if(activePosts[i].postId == postId){
                post = activePosts[i];
            }
        }

        return post;
    }


    function getPosts() external view returns (PostStruct[] memory){
        return activePosts;
    }

    function getDeletedPosts() external view returns(PostStruct[] memory){
        return inactivePosts;
    }

    function deletePost(uint256 postId)
        external returns (bool){
            require(authorOf[postId] == msg.sender, "Unauthorized entity");
             PostStruct memory post;
            for(uint256 i =0 ; i < activePosts.length; i++){
                if(activePosts[i].postId == postId){
                    post = activePosts[i];
                    activePosts[i].deleted = Deactivated.YES;
                    activePosts[i].updated = block.timestamp;
                    inactivePosts.push(activePosts[i]);
                    delPostOf[postId] = authorOf[postId];
                    delete activePosts[i];
                    delete authorOf[postId];
                }
            }

            postOf[msg.sender]--;

            inactivePostCounter++;
            activePostCounter--;

            emit Action(postId, 
            "POST DELETED",
            post,
            Deactivated.YES,
            msg.sender,
            block.timestamp
            );

            return true;
        }

        function restorDeletePost(
            uint256 postId,
            address author
        ) ownerOnly external returns (bool){
            require(delPostOf[postId] == author, "Unmatched Author");
            PostStruct memory post;
            for(uint i = 0 ; i<inactivePosts.length; i++){
                if(inactivePosts[i].postId == postId){
                    post = activePosts[i];
                    inactivePosts[i].deleted = Deactivated.NO;
                    inactivePosts[i].updated = block.timestamp;

                    activePosts.push(inactivePosts[i]);
                    delete inactivePosts[i];
                    authorOf[postId] = delPostOf[postId];
                    delete delPostOf[postId];
                }
            }

            postOf[author]++;
            inactivePostCounter--;
            activePostCounter++;


            emit Action(
                postId,
                "POST RESTORED",
                post,
                Deactivated.NO,
                msg.sender,
                block.timestamp
            );

            return true;

        }



}