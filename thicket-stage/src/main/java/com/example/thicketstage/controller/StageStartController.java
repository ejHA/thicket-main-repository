package com.example.thicketstage.controller;

import com.example.thicketstage.domain.StageStart;
import com.example.thicketstage.dto.request.RequestCreateStageStartDto;
import com.example.thicketstage.service.StageStartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stageStarts")
@RequiredArgsConstructor
public class StageStartController {
    private final StageStartService stageStartService;

    @PostMapping("")
    public ResponseEntity<?> createStageStart(@RequestBody @Valid RequestCreateStageStartDto stageStartDto) {
        List<StageStart> stageStart = stageStartService.createStageStart(stageStartDto);

        return new ResponseEntity<>("공연 일자 등록 성공!!", HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllDate() {
        return ResponseEntity.ok(stageStartService.getAllDate());
    }

//    @PatchMapping("update/{id}")
//    public ResponseEntity<?> updateStageStart(@PathVariable Long id,
//                                              @RequestBody @Valid RequestStageStartUpdateDto updateDto) {
//        stageStartService.updateStageStart(id, updateDto);
//
//        return ResponseEntity.ok("수정이 완료되었습니다.");
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStageStart(@PathVariable @Valid Long id) {
        stageStartService.deleteStageStart(id);

        return ResponseEntity.ok("삭제가 완료되었습니다.");
    }
}
