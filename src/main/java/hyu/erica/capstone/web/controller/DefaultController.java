package hyu.erica.capstone.web.controller;

import hyu.erica.capstone.api.ApiResponse;
import hyu.erica.capstone.api.code.status.SuccessStatus;
import hyu.erica.capstone.service.CsvImportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "기본", description = "기본 API")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class DefaultController {

    private final CsvImportService importService;

    @Operation(summary = "Health Check", description = "서버 상태 확인")
    @GetMapping("/health-check")
    public String healthCheck() {
        return "Hello, World!";
    }

    @Operation(summary = "[서버 개발 용] 어트랙션 파일 업로드", description = """
            ### 어트랙션 파일을 업로드합니다.
            
            ### Request Body
            - file: 어트랙션 파일
            
            """)
    @PostMapping("/upload/attractions")
    public ApiResponse<?> uploadAttractionFile(@RequestParam MultipartFile file) {
        importService.importAttraction(file);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}
