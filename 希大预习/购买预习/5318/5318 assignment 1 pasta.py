# -*- coding: utf-8 -*-
import getopt
import sys
import csv
import numpy as np
import pandas as pd
import re

def dateset_preparation(file_name):
    # 1, 3
    csvFile = pd.read_csv(file_name)
    original = csvFile.iloc[:,:]
    original.replace('?', np.nan, inplace=True)
    original.replace('class1', 0, inplace=True)
    original.replace('class2', 1, inplace=True)

    from sklearn.impute import SimpleImputer
    imp_mean = SimpleImputer(missing_values=np.nan , copy=True, strategy='mean')
    original = imp_mean.fit_transform(original)

    # 2
    from sklearn.preprocessing import MinMaxScaler
    scaler = MinMaxScaler(feature_range=(0, 1))
    original = scaler.fit_transform(original)

    # 4
    original = np.array(original)
    original = original.tolist()
    for i in range(0, len(original)):
        for j in range(0, len(original[0])-1):
            original[i][j] = "%.4f" %original[i][j]
        original[i][len(original[0])-1] = (int)(original[i][len(original[0])-1])
        
    return original

def print_pre_ata(original):
    for i in range(0, len(original)):
        for j in range(0, len(original[0])):
            print(original[i][j], end='')
            if j != (len(original[0])-1):
                print(',', end='')
        if i != (len(original)-1):
            print()
    return

def kNNClassifier(x, y, k):
    from sklearn.model_selection import StratifiedKFold
    cvKFold = StratifiedKFold(n_splits=10, shuffle=True, random_state=0)


    from sklearn import neighbors
    from sklearn.model_selection import cross_val_score
    knn = neighbors.KNeighborsClassifier(n_neighbors=k)
    scores = cross_val_score(knn, x, y, cv=cvKFold)
    print("{:.4f}".format(scores.mean()), end='')
    return scores, scores.mean()

def logregClassifier(X, y):
    from sklearn.model_selection import StratifiedKFold
    cvKFold = StratifiedKFold(n_splits=10, shuffle=True, random_state=0)


    from sklearn.linear_model import LogisticRegression
    from sklearn.model_selection import cross_val_score
    clf = LogisticRegression(random_state=0)
    scores = cross_val_score(clf, X, y, cv=cvKFold)
    print("{:.4f}".format(scores.mean()), end='')
    return scores, scores.mean()

def nbClassifier(X, y):
    from sklearn.model_selection import StratifiedKFold
    cvKFold = StratifiedKFold(n_splits=10, shuffle=True, random_state=0)


    from sklearn.naive_bayes import GaussianNB
    from sklearn.model_selection import cross_val_score
    clf = GaussianNB()
    scores = cross_val_score(clf, X, y, cv=cvKFold)
    print("{:.4f}".format(scores.mean()), end='')
    return scores, scores.mean()

def dtClassfier(X, y):
    from sklearn.model_selection import StratifiedKFold
    cvKFold = StratifiedKFold(n_splits=10, shuffle=True, random_state=0)


    from sklearn.tree import DecisionTreeClassifier
    from sklearn.model_selection import cross_val_score
    clf = DecisionTreeClassifier(criterion='entropy', random_state=0)
    scores = cross_val_score(clf, X, y, cv=cvKFold)
    print("{:.4f}".format(scores.mean()), end='')
    return scores, scores.mean()

def bagDTClassifier(X, y, n_estimators, max_samples, max_depth):
    from sklearn.model_selection import StratifiedKFold
    cvKFold = StratifiedKFold(n_splits=10, shuffle=True, random_state=0)

    from sklearn.ensemble import BaggingClassifier
    from sklearn.model_selection import cross_val_score
    from sklearn.tree import DecisionTreeClassifier

    clf = BaggingClassifier(base_estimator=DecisionTreeClassifier(criterion='entropy', max_depth=max_depth), n_estimators=n_estimators, max_samples=max_samples, random_state=0)
    scores = cross_val_score(clf, X, y, cv=cvKFold)
    print("{:.4f}".format(scores.mean()), end='')
    return scores, scores.mean()

def adaDTClassifier(X, y, n_estimators, learning_rate, max_depth):
    from sklearn.model_selection import StratifiedKFold
    cvKFold = StratifiedKFold(n_splits=10, shuffle=True, random_state=0)

    from sklearn.ensemble import AdaBoostClassifier
    from sklearn.model_selection import cross_val_score
    from sklearn.tree import DecisionTreeClassifier

    clf = AdaBoostClassifier(base_estimator=DecisionTreeClassifier(criterion='entropy', max_depth=max_depth), n_estimators=n_estimators, learning_rate=learning_rate, random_state=0)
    scores = cross_val_score(clf, X, y, cv=cvKFold)
    print("{:.4f}".format(scores.mean()), end='')
    return scores, scores.mean()

def gbClassifier(X, y, n_estimators, learning_rate):
    from sklearn.model_selection import StratifiedKFold
    cvKFold = StratifiedKFold(n_splits=10, shuffle=True, random_state=0)

    from sklearn.ensemble import GradientBoostingClassifier
    from sklearn.model_selection import cross_val_score

    clf = GradientBoostingClassifier(n_estimators=n_estimators, learning_rate=learning_rate, random_state=0)
    scores = cross_val_score(clf, X, y, cv=cvKFold)
    print("{:.4f}".format(scores.mean()), end='')
    return scores, scores.mean()

def bestRFClassifier(X, y):
    param_grid = {'n_estimators': [10, 20, 50, 100], 
                  'max_features': ['auto', 'sqrt', 'log2'], 
                  'max_leaf_nodes': [10, 20, 30]}
    
    from sklearn.model_selection import train_test_split
    X_train, X_test, y_train, y_test = train_test_split(X, y, stratify=y, random_state=0)
    
    from sklearn.model_selection import StratifiedKFold  
    cvKFold = StratifiedKFold(n_splits=10, shuffle = True, random_state = 0)
    
    from sklearn.model_selection import GridSearchCV
    from sklearn.ensemble import RandomForestClassifier
    grid_search = GridSearchCV(RandomForestClassifier(criterion='entropy', random_state = 0), param_grid, cv=cvKFold, return_train_score=True)
    grid_search.fit(X_train, y_train)
    value = grid_search.best_params_
    print(value['n_estimators'])
    print(value['max_features'])
    print(value['max_leaf_nodes'])
    print("{:.4f}".format(grid_search.best_score_))
    print("{:.4f}".format(grid_search.score(X_test, y_test)), end='')

def bestLinClassifier(X, y):
    param_grid = {'C': [0.001, 0.01, 0.1, 1, 10, 100], 
                  'gamma': [0.001, 0.01, 0.1, 1, 10, 100]}

    from sklearn.model_selection import train_test_split
    X_train, X_test, y_train, y_test = train_test_split(X, y, stratify=y, random_state=0)
    
    from sklearn.model_selection import StratifiedKFold  
    cvKFold = StratifiedKFold(n_splits=10, shuffle = True, random_state = 0)
    
    from sklearn.model_selection import GridSearchCV
    from sklearn.svm import SVC
    grid_search = GridSearchCV(SVC(kernel='linear', random_state=0), param_grid, cv=cvKFold)
    grid_search.fit(X_train, y_train)
    value = grid_search.best_params_
    print(value['C'])
    print(value['gamma'])
    print("{:.4f}".format(grid_search.best_score_))
    print("{:.4f}".format(grid_search.score(X_test, y_test)), end='')

if __name__==__name__ == "__main__":
    original = dateset_preparation(sys.argv[1])
    type = sys.argv[2]

    # split origin data to data(attribute) and target(class)
    data, target = np.split(original, [len(original[0])-1], axis=1)
    target = target.reshape(1,len(original))
    target = target[0]
    data = data.astype(np.float64)
    target = target.astype(np.float64)
    
    if type == 'NN':
        parament = open(sys.argv[3])
        parament.readline()
        k = parament.readline()
        k = (int)(k)
        kNNClassifier(data, target, k)
    elif type == 'LR':
        logregClassifier(data, target)
    elif type == 'NB':
        nbClassifier(data, target)
    elif type == 'DT':
        dtClassfier(data, target)
    elif type == 'BAG':
        temp = pd.read_csv(sys.argv[3])
        n_estimators = temp.iloc[0,0]
        max_samples = temp.iloc[0,1]
        max_depth = temp.iloc[0,2]
        bagDTClassifier(data, target, n_estimators, max_samples, max_depth)
    elif type == 'ADA':
        temp = pd.read_csv(sys.argv[3])
        n_estimators = temp.iloc[0,0]
        learning_rate = temp.iloc[0,1]
        max_depth = temp.iloc[0,2]
        adaDTClassifier(data, target, n_estimators, learning_rate, max_depth)
    elif type == 'GB':
        temp = pd.read_csv(sys.argv[3])
        n_estimators = temp.iloc[0,0]
        learning_rate = temp.iloc[0,1]
        gbClassifier(data, target, n_estimators, learning_rate)
    elif type == 'RF':
        bestRFClassifier(data, target)
    elif type == 'SVM':
        bestLinClassifier(data, target)
    elif type == 'P':
        print_pre_ata(original)