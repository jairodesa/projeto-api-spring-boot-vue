package com.projeto.auth.api.auth.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUsuario is a Querydsl query type for Usuario
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsuario extends EntityPathBase<Usuario> {

    private static final long serialVersionUID = 293111335L;

    public static final QUsuario usuario = new QUsuario("usuario");

    public final QEntidade _super = new QEntidade(this);

    public final StringPath cpf = createString("cpf");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final EnumPath<com.projeto.auth.api.auth.enums.SituacaoUsuario> situacao = createEnum("situacao", com.projeto.auth.api.auth.enums.SituacaoUsuario.class);

    public QUsuario(String variable) {
        super(Usuario.class, forVariable(variable));
    }

    public QUsuario(Path<? extends Usuario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsuario(PathMetadata metadata) {
        super(Usuario.class, metadata);
    }

}

