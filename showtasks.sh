#!/usr/bin/env bash

fail() {
   echo "There were errors"
}

end() {
   echo "Work is finished"
}

if ./runcrud.sh; then
  open -a "Google Chrome" "http://localhost:8080/crud/v1/tasks"
  end
else
   stop_tomcat
   fail
fi