@echo off
setlocal enabledelayedexpansion

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

aider --model openai/llama-3.3-70b --no-restore-chat-history --edit-format whole --yes --auto-commits --dirty-commits --no-show-model-warnings --message-file ralph.prompt.md

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