package ru.app.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TrackUser {


    @Around("@annotation(ru.gb.task_handler.aspect.TrackUserAction)")
    public Object getNode(ProceedingJoinPoint joinPoint) {
        // Извлекаем аргументы метода addNote

        // Создаем новую заметку

        // Изменяем описание заметки
        Object [] args = joinPoint.getArgs();
        args[0] = "Мухахаха";
        try {
            return joinPoint.proceed(args);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

    @AfterReturning(value = "@annotation(AfterUserAction)", returning = "returnValue")
    public void log(Object returnValue) {
        System.out.println("Note + " + returnValue.toString());
    }

}
