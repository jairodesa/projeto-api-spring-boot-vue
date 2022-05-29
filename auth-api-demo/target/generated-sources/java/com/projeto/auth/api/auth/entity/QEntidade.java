package com.projeto.auth.api.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEntidade is a Querydsl query type for Entidade
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QEntidade extends EntityPathBase<Entidade<?>> {

    private static final long serialVersionUID = -1782861885L;

    public static final QEntidade entidade = new QEntidade("entidade");

    public final SimplePath<Object> id = createSimple("id", Object.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QEntidade(String variable) {
        super((Class) Entidade.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QEntidade(Path<? extends Entidade> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QEntidade(PathMetadata metadata) {
        super((Class) Entidade.class, metadata);
    }

}

