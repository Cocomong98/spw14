package com.example.board;
import com.example.board.BoardVO;
import com.example.board.BoardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@ComponentScan(basePackages={"com.example.board.BoardDAO"})
public class BoardController {
    @Autowired
    BoardDAO boardDAO;

    @RequestMapping(value="/")

    public String home(){return "board/index";}

    @RequestMapping(value = "/board/list", method = RequestMethod.GET)
    public String boardlist(Model model){
        model.addAttribute("list", boardDAO.getBoardList());
        return "posts";
    }

    @RequestMapping(value = "/board/add", method = RequestMethod.GET)
    public String addPost(){
        return "addpostform";
    }

    @RequestMapping(value = "/board/addok", method = RequestMethod.POST)
    public String addPostOK(BoardVO vo){
        int i = boardDAO.insertBoard(vo);
        if(i == 0)
            System.out.println("데이터 추가 실패");
        else
            System.out.println("데이터 추가 성공!!");
        return "redirect:list";
    }

    @RequestMapping(value = "/board/editpost/{id}", method = RequestMethod.GET)
    public String editPost(@PathVariable("id") int id, Model model){
        System.out.println("id");
        BoardVO boardVO = boardDAO.getBoard(id);
        System.out.println(boardVO.getCategory());
        model.addAttribute("boardVO", boardVO);
        return "editform";
    }

    @RequestMapping(value = "board/editok", method = RequestMethod.POST)
    public String editPostOk(BoardVO vo){
        int i = boardDAO.updateBoard(vo);
        if(i == 0) System.out.println("데이터 수정 실패");
        else System.out.println("데이터 수정 성공!!!");
        return "redirect:list";
    }

    @RequestMapping(value = "board/deleteok/{id}", method = RequestMethod.GET)
    public String deletePost(@PathVariable("id") int id){
        int i = boardDAO.deleteBoard(id);
        if(i == 0) System.out.println("데이터 삭제 실패");
        else System.out.println("데이터 삭제 성공!!!");
        return "redirect:../list";
    }
}
