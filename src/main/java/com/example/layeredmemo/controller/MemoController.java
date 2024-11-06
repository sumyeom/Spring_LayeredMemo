package com.example.layeredmemo.controller;

import com.example.layeredmemo.dto.MemoRequestDto;
import com.example.layeredmemo.dto.MemoResponseDto;
import com.example.layeredmemo.entity.Memo;
import com.example.layeredmemo.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //@Controller + @ResponseBody
@RequestMapping("/memos") //Prefix
public class MemoController {

    // 주입된 의존성을 변경할 수 없어 객체의 상태를 안전하게 유지할 수 있다.
    private final MemoService memoService;

    /**
     * 생성자 주입
     * 클래스가 필요로 하는 의존성을 생성자를 통해 전달하는 방식
     * @param memoService @Service로 등록된 MemoService 구현체인 Impl
     */
    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto) {

        // Service Layer 호출, 응답

        return new ResponseEntity<>(memoService.saveMemo(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MemoResponseDto> findAllMemos(){
        return memoService.findAllMemos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoByID(
            @PathVariable Long id
    ){
        return new ResponseEntity<>(memoService.findMemoById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemo(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ){
        return new ResponseEntity<>(memoService.updateMemo(id, dto.getTitle(), dto.getContents()),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ){
        return new ResponseEntity<>(memoService.updateTitle(id,dto.getTitle(),dto.getContents()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemoById(@PathVariable Long id){

        memoService.deleteMemoById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}