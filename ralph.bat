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

echo =====================================
echo   AIDER RALPH LOOP STARTING
echo   Total iterations: %ITERATIONS%
echo =====================================

:loop

echo.
echo -------- Iteration !COUNT! / %ITERATIONS% --------

aider -v --model openai/llama-3.3-70b --editor-model ollama/deepseek-coder:6.7b --no-restore-chat-history --edit-format whole --yes --auto-commits --dirty-commits --no-show-model-warnings --message-file ralph.prompt.md

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