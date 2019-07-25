package kr.bbmm.stk.controllers;

import kr.bbmm.stk.domains.CylinderDTO;
import kr.bbmm.stk.domains.SodaLogDTO;
import kr.bbmm.stk.domains.service.SodaLogService;
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
    public String index(Model model) {
        CylinderDTO.ONE cylinderInfo = sodaLogService.findActivateCylinderInfo();

        model.addAttribute("cylinderInfo", cylinderInfo);

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

    @GetMapping(value = "/exhaust/{id}")
    public String exhaustGas(@PathVariable(value = "id") Long id) {
        sodaLogService.exhaustCylinder(id);
        return "redirect:/";
    }
}
