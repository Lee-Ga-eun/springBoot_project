package com.shop.shop.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUnivlist is a Querydsl query type for Univlist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUnivlist extends EntityPathBase<Univlist> {

    private static final long serialVersionUID = 384565186L;

    public static final QUnivlist univlist = new QUnivlist("univlist");

    public final NumberPath<Long> FIELD1 = createNumber("FIELD1", Long.class);

    public final StringPath name = createString("name");

    public QUnivlist(String variable) {
        super(Univlist.class, forVariable(variable));
    }

    public QUnivlist(Path<? extends Univlist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUnivlist(PathMetadata metadata) {
        super(Univlist.class, metadata);
    }

}

