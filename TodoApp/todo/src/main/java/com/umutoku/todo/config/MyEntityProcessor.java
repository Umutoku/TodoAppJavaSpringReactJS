package com.umutoku.todo.config;

import com.umutoku.todo.entity.Todo;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyEntityProcessor implements RepresentationModelProcessor<EntityModel<Todo>> {

    @Override
    public EntityModel<Todo> process(EntityModel<Todo> model) {
        return EntityModel.of(model.getContent());
    }

}
