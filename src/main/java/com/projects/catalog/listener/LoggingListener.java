package com.projects.catalog.listener;

import java.time.Duration;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingListener implements GraphQLServletListener {

  @Override
  public GraphQLServletListener.RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
    Instant start = Instant.now();
    log.info("Received graphql request");
    return new RequestCallback() {
      @Override
      public void beforeFlush(HttpServletRequest request, HttpServletResponse response) {
        RequestCallback.super.beforeFlush(request, response);
      }

      @Override
      public void onSuccess(HttpServletRequest request, HttpServletResponse response) {
        RequestCallback.super.onSuccess(request, response);
      }

      @Override
      public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
        RequestCallback.super.onError(request, response, throwable);
      }

      @Override
      public void onFinally(HttpServletRequest request, HttpServletResponse response) {
        log.info("Completed request. Time taken: {}", Duration.between(start, Instant.now()));
      }
    };
  }

}
