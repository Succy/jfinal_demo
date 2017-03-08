package cn.succy.jfinal.common;

import cn.succy.jfinal.blog.BlogController;
import cn.succy.jfinal.common.model._MappingKit;
import cn.succy.jfinal.index.IndexController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * @author Succy
 * @date 2017-03-07 21:17
 **/

public class DemoConfig extends JFinalConfig {

    public static void main(String[] args) {
        JFinal.start("src/main/webapp", 80, "/");
    }

    @Override
    public void configConstant(Constants constants) {
        PropKit.use("config.properties");
        constants.setDevMode(PropKit.getBoolean("devMode", false));
    }

    @Override
    public void configRoute(Routes routes) {
        routes.add("/", IndexController.class, "/index");
        routes.add("/blog", BlogController.class);
    }

    @Override
    public void configEngine(Engine engine) {
        engine.addSharedFunction("/common/_layout.html");
        engine.addSharedFunction("/common/_paginate.html");
    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin druid = createDruidPlugin();
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druid);

        plugins.add(druid);
        plugins.add(arp);

        _MappingKit.mapping(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }

    public static DruidPlugin createDruidPlugin() {
        return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
    }
}
