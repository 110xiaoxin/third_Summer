package cn.edu.hit.controller;

import cn.edu.hit.entity.Region;
import cn.edu.hit.entity.Result;
import cn.edu.hit.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping
    public Result add(@RequestBody @Validated(Region.Add.class) Region region){
        regionService.add(region);
        return Result.success();
    }

    @GetMapping
    public Result<List<Region>> list(){
        List<Region> regions= regionService.list();
        return Result.success(regions);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Region.Update.class) Region region){
        System.out.println(region);
        regionService.update(region);
        return Result.success();
    }

    @GetMapping("/detail")
    public Result<Region> detail(Integer id){
        Region region = regionService.findById(id);
        return Result.success(region);
    }

    @DeleteMapping
    public Result delete(Integer id){
        regionService.delete(id);
        return Result.success();
    }
}
