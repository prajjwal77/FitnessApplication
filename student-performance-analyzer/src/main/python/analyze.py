import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# Load scores
df = pd.read_csv('uploads/student_scores.csv')

subjects = df.columns[1:]
averages = df[subjects].mean()

# Grade prediction
df['Total'] = df[subjects].sum(axis=1)
df['Grade'] = pd.cut(df['Total'],
                     bins=[0, 150, 200, 250, 300],
                     labels=['D', 'C', 'B', 'A'])

weak_subjects = averages.nsmallest(3)

# Print insights
print("Average Scores:")
print(averages.to_string())
print("\nWeak Subjects:")
print(weak_subjects.to_string())
print("\nGrade Distribution:")
print(df['Grade'].value_counts().to_string())

# Save bar chart
plt.figure(figsize=(10, 6))
averages.plot(kind='bar', color='skyblue')
plt.title("Average Score per Subject")
plt.ylabel("Score")
plt.savefig("src/main/resources/static/chart.png")
