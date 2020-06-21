package com.bzdgs.dms.controller;

import com.bzdgs.dms.domain.Employee;
import com.bzdgs.dms.domain.User;
import com.bzdgs.dms.query.EmployeeQuery;
import com.bzdgs.dms.query.UserQuery;
import com.bzdgs.dms.service.IEmployeeService;
import com.bzdgs.dms.service.IUserService;
import com.bzdgs.dms.util.AjaxResult;
import com.bzdgs.dms.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description desc
 * @Author lq
 * @Date new Date()
 * @Version v1.0
 **/

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IUserService userService;

    @PostMapping("/user/page")
    public PageList<User> list(@RequestBody UserQuery userQuery){
        return userService.page(userQuery);
    }

    @PostMapping("/user/save")
    public AjaxResult save(@RequestBody User user){
        try {
            if(user.getId()!=null){
                userService.update(user);
            }else{

                user.setPassword("123");
                userService.insert(user);
            }
            return AjaxResult.me().setMsg("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.me().setSuccess(false).setMsg("保存失败");

    }

    @PostMapping("/employee/page")
    public PageList<Employee> list(@RequestBody EmployeeQuery employeeQuery){
        return employeeService.page(employeeQuery);
    }

    @DeleteMapping("/employee/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){

        try {
            employeeService.removeById(id);
            return AjaxResult.me().setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.me().setSuccess(false).setMsg("删除失败");

    }

    @PostMapping("/employee")
    public AjaxResult save(@RequestBody Employee employee){
        try {
            if(employee.getId()!=null){
                employeeService.updateById(employee);
            }else{
                employee.setPassword("123");
                employeeService.insert(employee);
            }
            return AjaxResult.me().setMsg("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxResult.me().setSuccess(false).setMsg("保存失败");

    }
}
