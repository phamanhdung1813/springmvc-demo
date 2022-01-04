package com.anhdungpham.controller.admin;

import com.anhdungpham.constant.SystemConstant;
import com.anhdungpham.dao.ICategoryDAO;
import com.anhdungpham.dto.NewDTO;
import com.anhdungpham.service.ICategoryService;
import com.anhdungpham.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anhdungpham.service.INewService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "newControllerOfAdmin")

public class NewController {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping(value = "/admin/news/list", method = RequestMethod.GET)
    // formUtil == @ModelAttribute
    public ModelAndView showList(@RequestParam("page") int page,
                                 @RequestParam("maxPageItem") int maxPageItem,
                                 HttpServletRequest request
    ) {
        NewDTO model = new NewDTO();
        model.setPage(page);
        model.setMaxPageItem(maxPageItem);

        ModelAndView mav = new ModelAndView("admin/news/list");
        Pageable pageable = new PageRequest(model.getPage() - 1, model.getMaxPageItem());
        model.setListResult(newService.findAll(pageable));
        model.setTotalItem(newService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

    @RequestMapping(value = "/admin/news/edit", method = RequestMethod.GET)
    public ModelAndView editList(@RequestParam(value = "id", required = false) Long id,
                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/news/edit");
        NewDTO model = new NewDTO();
        if (id != null) {
            model = newService.findById(id);
        }

        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("categories", categoryService.findAll());
        mav.addObject(SystemConstant.MODEL, model);
        return mav;
    }

}
