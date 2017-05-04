// ...075p.
package yes.joywins;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import yes.joywins.dao.BoardRepository;
import yes.joywins.dao.ReplyRepository;
import yes.joywins.domain.Board;
import yes.joywins.domain.Reply;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyTests {

	  @Autowired
	  BoardRepository boardRepo;
	  
	  @Autowired
	  ReplyRepository replyRepo;
	  
	  //@Test
	  public void testInsertBoard200(){
	    
	    for(int i = 1;i <= 200; i++){
	    
	      Board board = new Board();
	      board.setTitle("z제목 테스트용 "+ i);
	      board.setContent("z내용 테스트용 " +  i);
	      board.setWriter("zuser" + i% 10 );
	      
	      boardRepo.save(board);
	    }
	    
	  }
	  
	  //@Test
	  public void testAddReply(){
		  
		  for(int i = 1;i <= 200; i++){
			    
			  Reply reply = new Reply();
			  reply.setReplyText("zTest Reply "+ i+"... ");
			  reply.setReplyer("zguest id " + i);
			    
			  Board board = new Board();
			  board.setBno(1609L);
			    
			  reply.setBoard(board);
			    
			  replyRepo.save(reply);    
			    
		    }
	    
	  }
	  
	  //@Test
	  public void testModifyReply(){

		  Reply reply = replyRepo.findOne(1809L).get();
		  
		  reply.setReplyText("zModify Reply....." + reply.getRno() + " || " + reply.getUpdatedate());
	
		  replyRepo.save(reply);
	  }
	  
	  //@Test
	  public void testDeleteReply(){
	    
	    replyRepo.delete(1811L);
	    
	  }
	  
	  @Test
	  public void testBoardwithReply(){
	    
	    List<Object[]> result = boardRepo.findBoardWithReply(1609L);
	    
	    result.forEach(arr -> {
	      for(int i = 0; i < arr.length; i++){
	        System.out.println("testBoardwithReply : " + arr[i]);
	      }
	    });    
	    
	  }
	  
}
