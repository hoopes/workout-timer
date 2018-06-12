(ns workout-timer.effects.storage
  (:require [re-frame.core :as rf]
            [cljs.reader :as reader]
            [workout-timer.rn.core :as rn]))

(defn save-key
  [{:keys [key data on-success on-failure]}]
  (.setItem rn/AsyncStorage (pr-str key) (pr-str data)
            (fn [error]
              (if-not error
                (when on-success (rf/dispatch on-success))
                (when on-failure (rf/dispatch (conj on-failure error)))))))

(defn load-key
  [{:keys [key on-success on-failure]}]
  (.getItem rn/AsyncStorage (pr-str key)
            (fn [error result]
              (if-not error
                (try
                  (let [data (reader/read-string result)]
                    (println "DATA")
                    (println data)
                    (println on-success)
                    (when on-success (rf/dispatch (conj on-success data))))
                  (catch :default e
                    (when on-failure (rf/dispatch (conj on-failure e)))))
                (when on-failure (rf/dispatch (conj on-failure error)))))))

(defn remove-key
  [{:keys [key on-success on-failure]}]
  (.removeItem rn/AsyncStorage (pr-str key)
               (fn [error]
                 (if-not error
                   (when on-success (rf/dispatch on-success))
                   (when on-failure (rf/dispatch (conj on-failure error)))))))

(rf/reg-fx :storage/save-key save-key)
(rf/reg-fx :storage/load-key load-key)
(rf/reg-fx :storage/remove-key remove-key)
