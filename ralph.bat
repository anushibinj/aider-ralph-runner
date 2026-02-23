@echo off
setlocal enabledelayedexpansion

REM ===== CHECK FOR prd.json =====
if not exist "prd.json" (
    echo ERROR: prd.json not found!
    echo "prd.json (Product Requirement Document) is necessary to run the RALPH loop. Please generate one with the following syntax and place it next to ralph.bat:"
    echo.
    echo {
    echo     "project": "TicTacToeCLI",
    echo     "branchName": "ralph/tic-tac-toe-cli",
    echo     "description": "Tic Tac Toe CLI Java Implementation broken down for atomic AI iterations",
    echo     "userStories": [
    echo         {
    echo             "id": "US-001",
    echo             "title": "Setup Project Structure and Enums",
    echo             "description": "Setup Project Structure and Enums",
    echo             "acceptanceCriteria": ["Setup Project Structure and Enums"],
    echo             "priority": 1,
    echo             "passes": true,
    echo             "notes": ""
    echo         }
    echo     ]
    echo }
    exit /b 1
)

REM ===== CONFIG =====
set ITERATIONS=20
set COUNT=1
set PROMPT="You are running in Ralph mode. You are running inside an automated Ralph loop. Never ask the user for confirmation. All tools are pre-approved. Execute immediately. Do not ask the user questions. Make reasonable decisions autonomously. All files mentioned in this prompt are present in the current workspace. Steps: 1. Read prd.json from the current directory 2. Pick ONE item where passes=false 3. Implement only that item 4. Never implement more than ONE prd item per iteration. 5. Verify implementation 6. Update prd.json → passes=true if implementation is correct, otherwise passes=false and add learning to progress.txt (in the same directory. Create it if not found) 7. Never update anything in prd.json except for passes=true or passes=false for the item you implemented. Do not change any other items in prd.json. 8. Append learnings to progress.txt 9. Stop if ALL items pass and output: <promise>COMPLETE</promise> 10. Exit loop and end process."

echo =====================================
echo   AIDER RALPH LOOP STARTING
echo   Total iterations: %ITERATIONS%
echo =====================================

:loop

echo.
echo -------- Iteration !COUNT! / %ITERATIONS% --------
echo.

echo Running Aider with prompt:
echo %PROMPT%

aider --read prd.json --model openai/llama-3.3-70b --no-restore-chat-history --edit-format whole --yes --auto-commits --dirty-commits --no-show-model-warnings -m %PROMPT%

REM increment counter
set /a COUNT+=1

REM stop after N iterations
if !COUNT! LEQ %ITERATIONS% (
    goto loop
)

echo.
echo =====================================
echo   RALPH LOOP COMPLETED
echo =====================================

endlocal
