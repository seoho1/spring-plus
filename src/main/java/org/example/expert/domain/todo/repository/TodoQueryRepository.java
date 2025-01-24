package org.example.expert.domain.todo.repository;

import static org.example.expert.domain.todo.entity.QTodo.todo;

import com.querydsl.jpa.JPQLQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TodoQueryRepository {

    private final JPQLQueryFactory queryFactory;

    public Page<Todo> findAll(Pageable pageable) {
        List<Todo> content = queryFactory.selectFrom(todo)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(todo.id.desc())
                .fetch();

        long total = queryFactory.selectFrom(todo).fetchCount();

        return new PageImpl<>(content, pageable, total);
    }
}
