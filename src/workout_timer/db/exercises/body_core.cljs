(ns workout-timer.db.exercises.body_core)

(def exercises
  [

   ;; Core exercises
   {:id :russian-twists
    :title "Russian Twists"
    :description (str "Sit on the floor, with your heels touching the floor "
                      "or lifted (more advanced) and your hands at your "
                      "chest. Twist from side to side.")
    :category :core}

   {:id :tuck-ups
    :title "Tuck-Ups"
    :description (str "Lie on your back with arms straight over your head. "
                      "Crunch your legs into your chest as you lift your back "
                      "off the floor. Grab your legs, balancing on your "
                      "glutes. Return to starting position and repeat.")
    :category :core}

   {:id :plank-jacks
    :title "Plank Jacks"
    :description (str "Start in low plank position with feet hip-width apart. "
                      "Hop your feet out wider than hip-width, and then hop "
                      "back to the starting position. Keep your abs tight and "
                      "don't allow your butt to pop up above the height of "
                      "your shoulders.")
    :category :core}

  {:id :flutter-kicks
   :title "Flutter Kicks"
   :description (str "Lie on your back with your hands behind your head, "
                     "holding your head and shoulders up off the floor. "
                     "Keeping your legs long and straight, bring one leg into "
                     "the air while the other hovers parallel to the ground. "
                     "Keep switching legs continuously.")
   :category :core}

  {:id :supermans
   :title "Supermans"
   :description (str "Lie on belly, with hands either over head, to side, or "
                     "behind. Lift arms, head, and legs as high as possible "
                     "from the ground.")
   :category :core}

  {:id :planks
   :title "Planks"
   :description (str "Standard planks")
   :category :core}

  {:id :stiff-zombie
   :title "Stiff Zombie"
   :description (str "Stiff arms and legs, walk like a zombie, "
                     "alternating strides.")
   :category :core}

  {:id :bicycle-crunches
   :title "Bicycle Crunches"
   :description (str "Standard bicycle crunches.")
   :category :core}

  {:id :mountain-climbers
   :title "Mountain Climbers"
   :description (str "Standard mountain climbers")
   :category :core}

   ])
