(ns workout-timer.events.core
  (:require
    [re-frame.core :as rf]
    [clojure.spec.alpha :as s]
    [workout-timer.db.core :as db]))

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

(rf/reg-event-db
  :workout/set-workout
  rf/trim-v
  (fn [db [workout]]
    (assoc db :current-workout (:id workout))))
