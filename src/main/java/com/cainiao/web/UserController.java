package com.cainiao.web;

import com.cainiao.dto.BootStrapTableResult;
import com.cainiao.entity.User;
import com.cainiao.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    /*
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Integer offset, Integer limit) {
		LOG.info("invoke----------/user/list");
		int off = Optional.ofNullable(offset).map(offset1 -> offset1.intValue()).orElse(0);
		int lim = Optional.ofNullable(limit).map(limit1 -> limit1.intValue()).orElse(50);
		//offset = offset == null ? 0 : offset;//默认便宜0
		//limit = limit == null ? 50 : limit;//默认展示50条
		List<User> list = userService.getUserList(off, lim);
		model.addAttribute("userlist", list);
		return "userlist";
	}
	*/

    /**
     * 摒弃jsp页面通过ajax接口做到真正意义上的前后分离
     *
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public BootStrapTableResult<User> list(Integer offset, Integer limit) {
        LOG.info("invoke----------/user/list");
        offset = offset == null ? 0 : offset;//默认便宜0
        limit = limit == null ? 50 : limit;//默认展示50条
        List<User> list = userService.getUserList(Optional.ofNullable(offset).map(off -> off.intValue()).orElse(0),
                Optional.ofNullable(limit).map(lim -> lim.intValue()).orElse(50));
        return new BootStrapTableResult<User>(list);
    }

}
