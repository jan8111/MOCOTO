package com.xiaoi.shMonitor.controler;

import com.xiaoi.shMonitor.entity.SimReq;
import com.xiaoi.shMonitor.service.AudioService;
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
    AudioService audioService;

    @RequestMapping(value = "/" ,method = RequestMethod.GET)
    public ModelAndView list() {
        List<SimReq> content1 = audioService.findAll(new PageRequest(0, 300)).getContent();
        ModelAndView mvc = new ModelAndView("list_simReq");
        mvc.addObject("content", content1);
        return mvc;
    }

    @RequestMapping(value = "/simReqPreAdd" ,method = RequestMethod.GET)
    public ModelAndView simReqPreAdd() {
        return new ModelAndView("add_simReq");
    }

    @RequestMapping(value = "/simReqAdd" ,method = RequestMethod.POST)
    public ModelAndView simReqAdd(@ModelAttribute(value="simReq") SimReq simReq) {
        SimReq en = audioService.save(simReq);
        ModelAndView mvc = new ModelAndView("add_simReq");
        mvc.addObject("content", en);
        return mvc;
    }

    @RequestMapping(value = "/simReqDel"  )
    public void simReqDel(@RequestParam(required = true) String id) {
        audioService.del(id);
        //stodo list
    }




}
