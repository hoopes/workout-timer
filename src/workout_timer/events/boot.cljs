(ns workout-timer.events.boot
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

(defn boot-flow []
  {
   :first-dispatch [:boot/load-from-storage]

   ;; FIXME: Toggle the initialized flag in the db
   })

(rf/reg-event-fx
  :boot-app
  (fn [_ _]
    { :async-flow (boot-flow) }))

(rf/reg-event-fx
  :boot/load-from-storage
  (fn [_ _]
    {
     :storage/load-key {:key        :workout-timer
                        :on-success [:boot/load-success]
                        :on-failure [:boot/load-failure]}
    }))

;; On load success, use the default db as defaults,
;; and write the loaded data over top of it.
(rf/reg-event-db
  :boot/load-success
  rf/trim-v
  (fn [_ [loaded-data]]
    (println "BOOT ASYNC DATA LOAD SUCCESS!")
    (println loaded-data)
    (merge db/app-db loaded-data)))

(rf/reg-event-db
  :boot/load-failure
  rf/trim-v
  (fn [_ [err]]
    (println "BOOT ASYNC DATA LOAD FAILURE")
    (println err)
    ;; Um... error page or something?
    db/app-db))
