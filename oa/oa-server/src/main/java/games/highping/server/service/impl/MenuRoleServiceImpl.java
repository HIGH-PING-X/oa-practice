package games.highping.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import games.highping.server.mapper.MenuRoleMapper;
import games.highping.server.pojo.MenuRole;
import games.highping.server.pojo.RespBean;
import games.highping.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 菜单角色中间表 服务实现类
 * </p>
 *
 * @author noob
 * @since 2023-11-20
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        if (null == mids || 0 == mids.length) {
            return RespBean.success("更新成功!");
        }
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        if (result == mids.length) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
