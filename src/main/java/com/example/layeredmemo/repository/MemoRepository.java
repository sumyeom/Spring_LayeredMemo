package com.example.layeredmemo.repository;

import com.example.layeredmemo.dto.MemoResponseDto;
import com.example.layeredmemo.entity.Memo;

import java.util.List;

public interface MemoRepository {
    Memo saveMemo(Memo memo); // id가 없는 상태로 repository에 전달
    List<MemoResponseDto> findAllMemos();
    Memo findMemoById(Long id);
    void deleteMemoById(Long id);

}
