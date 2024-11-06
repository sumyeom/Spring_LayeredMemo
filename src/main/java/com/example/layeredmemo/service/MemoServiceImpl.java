package com.example.layeredmemo.service;

import com.example.layeredmemo.dto.MemoRequestDto;
import com.example.layeredmemo.dto.MemoResponseDto;
import com.example.layeredmemo.entity.Memo;
import com.example.layeredmemo.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Annotation @Service는 @Component와 같다, Spring Bean으로 등록한다는 뜻.
 * Spring Bean으로 등록되면 다른 클래스에서 주입하여 사용할 수 있다.
 * 명시적으로 Service Layer 라는것을 나타낸다.
 * 비지니스 로직을 수행한다.
 */
@Service
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public MemoResponseDto saveMemo(MemoRequestDto dto) {

        // 요청받은 데이터로 MEMO 객체 생성 ID 없음
        // option + command + v
        Memo memo = new Memo(dto.getTitle(), dto.getContents());

        // DB 에 저장
        Memo savedMemo = memoRepository.saveMemo(memo);

        return new MemoResponseDto(savedMemo) ;
    }

    @Override
    public List<MemoResponseDto> findAllMemos() {
        List<MemoResponseDto> allMemos = memoRepository.findAllMemos();
        return allMemos;
        //return memoRepository.findAllMemos();
    }

    @Override
    public MemoResponseDto findMemoById(Long id) {
        Memo memo = memoRepository.findMemoById(id);

        if(memo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateMemo(Long id, String title, String contents) {

        Memo memo = memoRepository.findMemoById(id);

        if(memo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        if(title == null || contents == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contecnt are required values.");
        }

        memo.update(title, contents);

        return new MemoResponseDto(memo);
    }

    @Override
    public MemoResponseDto updateTitle(Long id, String title, String contents) {

        Memo memo = memoRepository.findMemoById(id);

        if(memo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        if(title == null || contents != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and contecnt are required values.");
        }

        memo.updateTitle(title);

        return new MemoResponseDto(memo);
    }

    @Override
    public void deleteMemoById(Long id) {
        Memo memo = memoRepository.findMemoById(id);
        if(memo == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }
        memoRepository.deleteMemoById(id);
    }
}