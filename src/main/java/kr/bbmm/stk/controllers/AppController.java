package kr.bbmm.stk.controllers;

import kr.bbmm.stk.domains.SodaLog;
import kr.bbmm.stk.domains.SodaLogDTO;
import kr.bbmm.stk.domains.service.SodaLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    private SodaLogService sodaLogService;

    public AppController(SodaLogService sodaLogService) {
        this.sodaLogService = sodaLogService;
    }

    @GetMapping(value = "/")
    public String index(
            Model model,
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        Sort sort = new Sort(Sort.Direction.DESC, "buildAt");
        Page<SodaLogDTO.LIST> sodaLogPage = sodaLogService.findAll(PageRequest.of(offset, size, sort));

        model.addAttribute("page", sodaLogPage);
        model.addAttribute("totalPushCount", sodaLogPage.get().mapToInt(i -> i.getPushCount()).sum());

        return "index";
    }

    @PostMapping(value = "/soda")
    public String insertSodaLog(SodaLogDTO.FORM_DATA formData) {
        sodaLogService.save(formData.toSodaLog());
        return "redirect:/";
    }

    @PutMapping(value = "/soda")
    public String updateSodaLog(SodaLogDTO.FORM_DATA formData) {
        sodaLogService.save(formData.toSodaLog());
        return "redirect:/";
    }

    @DeleteMapping(value = "/soda/{id}")
    public String deleteSodaLog(@PathVariable(value = "id") Long id) {
        sodaLogService.delete(id);
        return "redirect:/";
    }
}
