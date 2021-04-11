
# -*- coding: utf-8 -*-
"""
Created on Mon Feb  3 10:06:58 2020

@author: hi
"""

import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import pickle
from sklearn.model_selection import train_test_split, GridSearchCV

import warnings 
warnings.simplefilter("ignore")




#importing dataset
data = pd.read_csv('dataset.csv')

X = data.iloc[:, :-1]
y = data.iloc[:, -1]
X1_train, X1_test, y1_train, y1_test = train_test_split(X, y, test_size=0.2, random_state=0)

from sklearn.ensemble import RandomForestClassifier
regressor = RandomForestClassifier()

#Fitting model with trainig data
regressor.fit(X1_train,y1_train)

# Saving model to disk
pickle.dump(regressor, open('model.pkl','wb'))

# Loading model to compare the results
model = pickle.load(open('model.pkl','rb'))