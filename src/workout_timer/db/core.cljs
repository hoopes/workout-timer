(ns workout-timer.db.core
  (:require [clojure.spec.alpha :as s]
            [workout-timer.db.exercises.core :as e]
            [workout-timer.db.routing.core :as r]))

;; spec of app-db
;(s/def ::greeting string?)
;(s/def ::app-db
  ;(s/keys :req-un [::greeting]))

;; initial state of app-db
(def app-db
  {
   :app-initialized false

   ;; The map of id to exercise for all available exercises
   :exercises (zipmap (map :id e/exercises) e/exercises)

   ;; The id of the current exercise chosen
   :current-exercise nil

   ;; A map of id to workout
   :workouts {}
   ;:workouts {:a 1 :b 2 :c 3 :d 4 :e 5 :f 6 :g 7 :h 8 :i 9 :j 10 :k 11 :l 12 :m 13 :n 14}

   :current-workout nil

   :routing r/routing-state
   })
