package com.example.thicketstage.service;

import com.example.thicketstage.domain.Stage;
import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.dto.response.ResponseStageStartDto;
import com.example.thicketstage.repository.StageRepository;
import com.example.thicketstage.repository.StageStartRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StageStartServiceImpl implements StageStartService {

    private final StageStartRepository stageStartRepository;
    private final StageRepository stageRepository;

    @Override
    @Transactional
    public List<StageStart> createStageStart(RequestCreateStageStartDto dto) {

        Stage stage = stageRepository.findByUuid(dto.getStageUuid())
                .orElseThrow(() -> new EntityNotFoundException("해당 UUID의 Stage를 찾을 수 없습니다."));

        List<StageStart> stageStarts = dto.getStageStartDtos().stream()
                .map(ss -> StageStart.createStageStart(ss.getDate(), ss.getTimes(), stage))
                .toList();

        return stageStartRepository.saveAll(stageStarts);
    }

    @Override
    public List<ResponseStageStartDto> getAllDate(){
        List<StageStart> findStageStarts = stageStartRepository.findAll();
        return findStageStarts.stream().map(ResponseStageStartDto::new).toList();
    }

//    @Override
//    @Transactional
//    public void updateStageStart(Long id, RequestStageStartUpdateDto stageStartUpdateDto){
//
//        Optional<StageStart> optionalStageStart = stageStartRepository.findById(id);
//
//        if(optionalStageStart.isEmpty()){
//            throw new EntityNotFoundException("해당 Id값의 일정이 없습니다.");
//        }
//        StageStart stageStart = optionalStageStart.get();
//
//        stageStart.updateStageStart(stageStartUpdateDto);
//    }

    @Override
    public void deleteStageStart(Long id) {
        Optional<StageStart> optionalStageStart = stageStartRepository.findById(id);

        if(optionalStageStart.isEmpty()){
            throw new EntityNotFoundException("해당 Id값의 일정이 없습니다.");
        }

        StageStart stageStart = optionalStageStart.get();

        stageStart.deleteStageStart();
        stageStartRepository.delete(stageStart);
    }
}