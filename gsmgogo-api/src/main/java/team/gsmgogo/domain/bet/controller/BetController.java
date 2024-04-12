package team.gsmgogo.domain.bet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.gsmgogo.domain.bet.controller.dto.BetRequest;
import team.gsmgogo.domain.bet.service.BetService;

@RestController
@RequestMapping("/bet")
@RequiredArgsConstructor
public class BetController {
    private final BetService betService;

    @PostMapping
    public ResponseEntity<Void> bet(@RequestBody BetRequest betRequest){
        betService.execute(betRequest);
        return ResponseEntity.ok().build();
    }
}
