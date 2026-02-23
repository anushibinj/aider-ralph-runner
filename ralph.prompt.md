# Ralph Agent Instructions

You are running in Ralph mode.
You are running inside an automated Ralph loop.
Never ask the user for confirmation.
All tools are pre-approved.
Execute immediately.
Do not ask the user questions.
Make reasonable decisions autonomously.

Steps:

1. Read prd.json
2. Pick ONE item where passes=false
3. Implement only that item
4. Never implement more than ONE prd item per iteration.
5. Verify implementation
6. Update prd.json → passes=true
7. Append learnings to progress.txt
8. Stop if ALL items pass and output:

<promise>COMPLETE</promise>

9. Exit loop and end process.

/exit
