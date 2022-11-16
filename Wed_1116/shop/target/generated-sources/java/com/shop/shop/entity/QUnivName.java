package com.shop.shop.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUnivName is a Querydsl query type for UnivName
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUnivName extends EntityPathBase<UnivName> {

    private static final long serialVersionUID = 383663567L;

    public static final QUnivName univName = new QUnivName("univName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QUnivName(String variable) {
        super(UnivName.class, forVariable(variable));
    }

    public QUnivName(Path<? extends UnivName> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUnivName(PathMetadata metadata) {
        super(UnivName.class, metadata);
    }

}

