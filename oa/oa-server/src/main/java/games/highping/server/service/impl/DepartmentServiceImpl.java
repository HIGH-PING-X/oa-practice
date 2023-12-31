package games.highping.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import games.highping.server.mapper.DepartmentMapper;
import games.highping.server.pojo.Department;
import games.highping.server.pojo.RespBean;
import games.highping.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    @Override
    public RespBean addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (1 == dep.getResult()) {
            return RespBean.success("添加成功!", dep);
        }
        return RespBean.error("添加失败!");
    }

    @Override
    public RespBean deleteDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDep(dep);
        if (-2 == dep.getResult()) {
            return RespBean.error("该部门下还有子部门，删除失败!");
        } else if (-1 == dep.getResult()) {
            return RespBean.error("该部门下还有员工，删除失败!");
        } else if (1 == dep.getResult()) {
            return RespBean.success("删除成功!");
        }
        return null;
    }
}
