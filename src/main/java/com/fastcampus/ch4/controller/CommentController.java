package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService service;

    //댓글을 수하는 메서드
    @PatchMapping("/comments/{cno}")
    @ResponseBody
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto, HttpSession session){
        String commenter = (String)session.getAttribute("id");
        dto.setCno(cno);
        System.out.println("dto = " + dto);

        try {
            if(service.modify(dto) != 1)
                throw new Exception("modify failed");

            return new ResponseEntity<>("modify ok", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("modify error", HttpStatus.BAD_REQUEST);
        }
    }

    //댓글을 등록하는 메서드
    @PostMapping("/comments")
    @ResponseBody
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session){
        String commenter = (String)session.getAttribute("id");
        dto.setCommenter(commenter);
        dto.setBno(bno);
        System.out.println("dto = " + dto);

        try {
            if(service.write(dto) != 1)
                throw new Exception("write failed");

            return new ResponseEntity<>("write ok", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("write error", HttpStatus.BAD_REQUEST);
        }
    }

    //지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}")
    @ResponseBody
    public ResponseEntity<String> remove(@PathVariable Integer bno, Integer cno, HttpSession session){
        String commenter = (String)session.getAttribute("id");

        try {
            int rowCnt = service.remove(cno, bno, commenter);

            if(rowCnt != 1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("delete ok", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("delete error", HttpStatus.BAD_REQUEST);
        }
    }

    //지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments")
    @ResponseBody public ResponseEntity<List<CommentDto>> list(Integer bno){
        List<CommentDto> list = null;
        try{
            list = service.getList(bno);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);

        }
    }
}
