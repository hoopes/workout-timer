(ns workout-timer.db.exercises.cardio)

(def exercises
  [

   ;; Cardio
   {:id :lateral-lunges-with-hops
    :title "Lateral Lunges with Hops"
    :description (str "Step your left leg out to your side for a lateral "
                      "lunge, keeping your right leg straight. Bring left leg "
                      "up to a 90-degree angle and hop on your right leg. "
                      "Repeat on each side.")
    :category :cardio}

   {:id :squat-thrusts
    :title "Squat Thrusts"
    :description (str "From standing position, drop hands to the floor and "
                      "kick feet back, so you're in plank position. Hop your "
                      "feet back to center and return to standing.")
    :category :cardio}

   {:id :burpees
    :title "Burpees"
    :description (str "Start standing. Place your hands on the ground, wrists "
                      "underneath shoulders and jump your feet back to high "
                      "plank position. Drop your chest to the ground. Then, "
                      "without arching your back, push yourself back up and "
                      "jump your feet back up to your hands. Explode off the "
                      "ground to perform a hop at the top.")
    :category :cardio}

   {:id :plyo-lunges
    :title "Plyo Lunges"
    :description (str "Plyometric lunges, jumping and switching feet.")
    :category :cardio}

  {:id :jumping-jacks
   :title "Jumping Jacks"
   :description (str "Standard jumping jacks, fast.")
   :category :core}
   ])
