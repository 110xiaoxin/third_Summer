package cn.edu.hit.controller;

import cn.edu.hit.entity.Facility;
import cn.edu.hit.entity.PageBean;
import cn.edu.hit.entity.Result;
import cn.edu.hit.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facility")
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @PostMapping
    public Result add(@RequestBody @Validated(Facility.Add.class) Facility facility){
        facilityService.add(facility);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Facility.Update.class) Facility facility){
        facilityService.update(facility);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<Facility>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer regionId,
            @RequestParam(required = false) String state){
        PageBean<Facility> pb = facilityService.list(pageNum,pageSize,regionId,state);
        return Result.success(pb);
    }

    @GetMapping("/detail")
    public Result<Facility> detail(Integer id){
        Facility facility = facilityService.findById(id);
        return Result.success(facility);
    }

    @DeleteMapping
    public Result delete(Integer id){
        facilityService.delete(id);
        return Result.success();
    }
}
