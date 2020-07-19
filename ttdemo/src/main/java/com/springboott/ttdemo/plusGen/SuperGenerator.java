package com.springboott.ttdemo.plusGen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.springboott.ttdemo.common.exception.CustomException;

import java.util.*;

public class SuperGenerator {

    /**
     * 获取GlobalConfig
     *
     * @return
     */
    protected GlobalConfig getGlobalConfig(String author, Integer seniority) {
        return new GlobalConfig()
                // 生成文件的输出目录（默认值：D 盘根目录）
                .setOutputDir(getJavaPath(seniority))
                // 是否覆盖已有文件（默认值：false）
                .setFileOverride(true)
                // 是否打开输出目录（默认值：true）
                .setOpen(false)
                // 是否在xml中添加二级缓存配置（默认值：false）
                .setEnableCache(false)
                // 开启 Kotlin 模式（默认值：false）
                .setKotlin(false)
                // 开启 swagger2 模式（默认值：false）
                .setSwagger2(true)
                // 开启 ActiveRecord 模式（默认值：false）
                .setActiveRecord(false)
                // 开启 BaseResultMap（默认值：false）
                .setBaseResultMap(true)
                // 开启 baseColumnList（默认值：false）
                .setBaseColumnList(true)
                // 时间类型对应策略（默认值：TIME_PACK）
                .setDateType(DateType.TIME_PACK)
                //作者
                .setAuthor(author)
                //自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName("%s")
                .setXmlName("%sMapper")
                .setMapperName("%sMapper")
                .setServiceImplName("%sServiceImpl")
                .setServiceName("%sService")
                .setControllerName("%sController");
    }

    /**
     * 获取DataSourceConfig
     *
     * @return
     */
    protected DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                // 数据库类型
                .setDbType(DbType.MYSQL)
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        if ("bit".equals(fieldType.toLowerCase())) {
                            return DbColumnType.BOOLEAN;
                        }
                        if ("tinyint".equals(fieldType.toLowerCase())) {
                            return DbColumnType.BOOLEAN;
                        }
                        if ("date".equals(fieldType.toLowerCase())) {
                            return DbColumnType.LOCAL_DATE;
                        }
                        if ("time".equals(fieldType.toLowerCase())) {
                            return DbColumnType.LOCAL_TIME;
                        }
                        if ("datetime".equals(fieldType.toLowerCase())) {
                            return DbColumnType.LOCAL_DATE_TIME;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                })
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("root")
                .setUrl("jdbc:mysql://127.0.0.1:3306/mytestdb?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false");
    }

    /**
     * 获取StrategyConfig
     *
     * @param tableName
     * @return
     */
    protected StrategyConfig getStrategyConfig(String tablePrefix, String... tableName) {
        List<TableFill> tableFillList = getTableFills();
        return new StrategyConfig()
                // 全局大写命名
                .setCapitalMode(false)
                // 去除表前缀
                .setTablePrefix(tablePrefix)
                //去除字段前缀
                //.setFieldPrefix("")
                // 表名生成策略
                .setNaming(NamingStrategy.underline_to_camel)
                // 需要生成的表
                //.setInclude(new String[] { "user" })
                .setInclude(tableName)
                // 自定义实体父类
//                .setSuperEntityClass("com.fangzhizun.framework.model.convert.Convert")
                // 自定义实体，公共字段
                //.setSuperEntityColumns("id")
                // 表填充字段
                .setTableFillList(tableFillList)
                // 自定义 mapper 父类
                //.setSuperMapperClass("com.fangzhizun.framework.mapper.BaseMapper")
                // 自定义 controller 父类
                .setSuperControllerClass("com.springboott.ttdemo.config.controller.SuperController")
                // 自定义 service 实现类父类
                .setSuperServiceImplClass(null)
                //.setSuperServiceImplClass("com.fangzhizun.framework.service.impl.BaseServiceImpl")
                // 自定义 service 接口父类
                .setSuperServiceClass(null)
                //.setSuperServiceClass("com.fangzhizun.framework.service.BaseService")
                // 【实体】是否生成字段常量（默认 false）
                .setEntityColumnConstant(false)
                // 【实体】是否为构建者模型（默认 false）
                .setEntityBuilderModel(false)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                .setEntityBooleanColumnRemoveIsPrefix(true)
                // 生成 @RestController 控制器
                .setRestControllerStyle(true)
                // 是否生成实体时，生成字段注解
                .setEntityTableFieldAnnotationEnable(false);
    }

    /**
     * 获取TableFill策略
     *
     * @return
     */
    protected List<TableFill> getTableFills() {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("createTime", FieldFill.INSERT));
        tableFillList.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("createUid", FieldFill.INSERT));
        tableFillList.add(new TableFill("updateUid", FieldFill.INSERT_UPDATE));
        return tableFillList;
    }

    /**
     * 获取PackageConfig
     *
     * @return
     */
    protected PackageConfig getPackageConfig() {
        return new PackageConfig()
                .setParent("com.springboott.ttdemo")
                .setController("controller")
                .setEntity("po")
                .setMapper("dao")
                .setXml("mapper")
                .setService("service")
                .setServiceImpl("service.impl");
    }

    /**
     * 获取InjectionConfig
     *
     * @return
     */
    protected InjectionConfig getInjectionConfig(Integer seniority) {
        /*return new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };*/
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(
                "/templates/mapper.xml.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                //resource下的mapper
                return getResourcePath(seniority) + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
//                return getJavaPath(seniority)+"/com/springboott/ttdemo" + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        }));
    }

    /**
     * 获取根目录
     *
     * @return
     */
    private String getRootPath(Integer seniority) {
        String path;
        switch (seniority) {
            case 1:
                //生成到项目中（大佬采用此法）
                path = System.getProperty("user.dir");
                break;
            case 2:
                //生成到D盘根目录（新手推荐的用法）
                path = "D:";
                break;
            default:
                throw new CustomException("Error: This generator method is not supported.");
        }
        return path;
    }

    /**
     * 获取JAVA目录
     *
     * @return
     */
    protected String getJavaPath(Integer seniority) {
        String javaPath = getRootPath(seniority) + "/src/main/java";
        System.err.println(" Generator Java Path:【 " + javaPath + " 】");
        return javaPath;
    }

    /**
     * 获取Resource目录
     *
     * @return
     */
    protected String getResourcePath(Integer seniority) {
        String resourcePath = getRootPath(seniority) + "/src/main/resources";
        System.err.println(" Generator Resource Path:【 " + resourcePath + " 】");
        return resourcePath;
    }

    /**
     * 获取TemplateConfig
     *
     * @return
     */
    protected TemplateConfig getTemplateConfig() {
        return new TemplateConfig().setXml(null);
    }

    /**
     * 获取AutoGenerator
     *
     * @param tableName
     * @return
     */
    protected AutoGenerator getAutoGenerator(String author, Integer seniority, String tablePrefix, String... tableName) {
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig(author, seniority))
                // 数据源配置
                .setDataSource(getDataSourceConfig())
                // 策略配置
                .setStrategy(getStrategyConfig(tablePrefix, tableName))
                // 包配置
                .setPackageInfo(getPackageConfig())
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                .setCfg(getInjectionConfig(seniority))
                // 模板配置
                .setTemplate(getTemplateConfig());
    }

    /**
     * <p>
     * MySQL generator
     * </p>
     */
    public void generator(String author, Integer seniority, String tablePrefix, String... tableName) {
        // 代码生成器
        AutoGenerator mpg = getAutoGenerator(author, seniority, tablePrefix, tableName);
        mpg.execute();
        if (tableName == null) {
            System.err.println(" Generator Success !");
        } else {
            for (int i = 0; i < tableName.length; i++) {
                System.err.println(" TableName【 " + tableName[i] + " 】" + "Generator Success !");
            }
        }
    }


}
