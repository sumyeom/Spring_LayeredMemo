package com.example.layeredmemo.service;

import com.example.layeredmemo.dto.MemoRequestDto;
import com.example.layeredmemo.dto.MemoResponseDto;

import java.util.List;

public interface MemoService {
    MemoResponseDto saveMemo(MemoRequestDto dto);
    List<MemoResponseDto> findAllMemos();
    MemoResponseDto findMemoById(Long id);
    MemoResponseDto updateMemo(Long id, String title, String contents);
    MemoResponseDto updateTitle(Long id, String title, String contents);
    void deleteMemoById(Long id);
}