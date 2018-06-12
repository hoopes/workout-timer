(ns workout-timer.events.core
  (:require [re-frame.core :as rf]
            ;[clojure.spec.alpha :as s]
            [workout-timer.db.core :as db]
            [workout-timer.events.boot]
            [workout-timer.events.workout]))

;; -- Interceptors ------------------------------------------------------------
;;
;; See https://github.com/Day8/re-frame/blob/master/docs/Interceptors.md
;;
;(defn check-and-throw
;"Throw an exception if db doesn't have a valid spec."
;[spec db [event]]
;(when-not (s/valid? spec db)
;(let [explain-data (s/explain-data spec db)]
;(throw (ex-info (str "Spec check after " event " failed: " explain-data) explain-data)))))

;(def validate-spec
;(if goog.DEBUG
;(after (partial check-and-throw ::db/app-db))
;[]))

;; -- Handlers --------------------------------------------------------------

(rf/reg-event-db
  :initialize-db
  ;validate-spec
  (fn [_ _]
    db/app-db))

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
