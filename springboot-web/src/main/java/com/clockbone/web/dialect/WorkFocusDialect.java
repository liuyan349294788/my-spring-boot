package com.clockbone.web.dialect;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
import org.thymeleaf.processor.IProcessor;

import java.util.LinkedHashSet;
import java.util.Set;

public class WorkFocusDialect extends AbstractProcessorDialect {

    //private final IExpressionObjectFactory EXPRESSION_OBJECTS_FACTORY = new WorkFocusExpressionFactory();


    private static final String DIALECT_NAME = "workfocus";

    private static final String PREFIX = "wlf";

    public static final int PROCESSOR_PRECEDENCE = 1000;

    public WorkFocusDialect() {
        super(DIALECT_NAME, PREFIX, PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        LinkedHashSet<IProcessor> processors = new LinkedHashSet<IProcessor>();
        processors.add(new Sample3ElementTagProcessor(dialectPrefix));//添加我们定义的标签
        //添加自定义处理器
        processors.add(new SampleAttributeTagProcessor(dialectPrefix));

        //processors.add(new SampleElementTagProcessor(dialectPrefix));
        return processors;
    }

}
