package com.example.layeredmemo.entity;

import com.example.layeredmemo.dto.MemoRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Memo {

    @Setter //변경해야되는 값만 Setter 해줘야되니까 변수 위에다가 선언
    private Long id;
    private String title;
    private String contents;

    public Memo(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public void update(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public void updateTitle(String title){
        this.title = title;
    }
}