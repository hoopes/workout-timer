(ns workout-timer.ops.storage.core
  (:require [re-frame.core :as rf]))

;; FIXME: Should we store routing state? I actually don't think so - loading
;; the app from the home state seems ok?
(def storage-keys
  [:workouts :current-workout])

(rf/reg-event-fx
  :store-db
  (fn [cofx _]
    (let [db (:db cofx)
          to-store (select-keys db storage-keys)]

      ;(println "STORING DATA")
      ;(println db)
      ;(println to-store)

      {:storage/save-key {:key :workout-timer
                          :data to-store
                          :on-success [:db-store-success]
                          :on-failure [:db-store-failure]}})))

(rf/reg-event-fx
  :db-store-success
  (fn [_ _]
    (println "DB STORE SUCCESS!")))

(rf/reg-event-fx
  :db-store-failure
  (fn [_ _]
    (println "DB STORE FAILURE!")))
