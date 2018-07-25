package com.xiaoi.shMonitor.controler;

import com.xiaoi.shMonitor.entity.SimReq;
import com.xiaoi.shMonitor.service.SimReqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SimReqController {

    @Autowired
    SimReqService audioService;

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public ModelAndView list() {
        List<SimReq> content1 = audioService.findAll(new PageRequest(0, 1000)).getContent();
        ModelAndView mvc = new ModelAndView("list_simReq");
        mvc.addObject("content", content1);
        return mvc;
    }

    @RequestMapping(value = "/simReqPreSave" ,method = RequestMethod.GET)
    public ModelAndView simReqPreSave(@RequestParam(required = false) String id) {
        ModelAndView mvc = new ModelAndView("save_simReq");
        if(id!=null){
            SimReq en =  audioService.findOne(id);
            mvc.addObject("simReq", en);
        }
        return mvc;

    }

    @RequestMapping(value = "/simReqSave" ,method = RequestMethod.POST)
    public ModelAndView simReqSave(@ModelAttribute(value="simReq") SimReq simReq) {
        SimReq en = audioService.save(simReq);
        ModelAndView mvc = new ModelAndView("save_simReq");
        mvc.addObject("content", en);
        return mvc;
    }

    @RequestMapping(value = "/simReqDel"  )
    public ModelAndView simReqDel(@RequestParam(required = true) String id) {
        SimReq en =   audioService.del(id);
        ModelAndView mvc = new ModelAndView("del_simReq");
        mvc.addObject("simReq", en);
        return mvc;
    }




}
