# RALPH mode using aider and a custom model

This project is a Proof of Concept for how you can run RALPH mode using the aider tool and a custom model from ollama/OpenAI or any other model that aider supports.

I have demonstrated this by asking continuously prompting Aider to write a Java based Tic Tac Toe application from scratch.

## API overview

The main runner command (inside `ralph.bat`) will look like:

```bash
aider -v --model openai/llama-3.3-70b --editor-model ollama/deepseek-coder:6.7b --no-restore-chat-history --edit-format whole --yes --auto-commits --dirty-commits --no-show-model-warnings --message-file ralph.prompt.md
```

Here is the explanation of some of the important flags:

- **--model openai/llama-3.3-70b**: Llama 3 model for code generation
- **--editor-model ollama/deepseek-coder:6.7b**: Deepseek Coder model for editing operations like finding diff, etc. Note that you may remove this argument and let the main model do all of the work. I found that the speed was better this way.
- **--no-restore-chat-history**: To start every iteration with fresh context
- **--edit-format whole**: This was needed so that the model reads the whole code in every iteration instead of just a small diff. This was crucial because the model that I used kept hallucinating otherwise (because it didn't have enough code context)
- **--yes**: To run in autonomous mode answering all questions as "Yes"
- **--auto-commits & --dirty-commits**: To perform commits at the end of every iteration
- **--no-show-model-warnings**: To ignore and hide all warnings related to using models that might not be the best for generating code
- **--message-file ralph.prompt.md**: The file with the RALPH mode instructions that executes in every iteration of the RALPH loop

## Pre-requisites

1. Aider CLI from https://github.com/Aider-AI/aider
2. Access to an LLM that Aider supports - https://aider.chat/docs/llms.html

## How to run

### Copy necessary files to your workspace

RALPH loop requires 2 files to run:

1. prd.json
2. ralph.bat

Generate the first one (more instructions in the below section). Copy the second one from this repo.

### Generate `prd.json`

If you don't have it already, generate `prd.json` (the Product Requirement document) by following syntax below. Make sure to break the tasks into as tiny chunks as possible. The larger the number of tasks/loops, the better the output.

You can use the following prompt as a starter inside CoPilot using one of the better models like Claude Opus. Using CoPilot is highly recommended for PRD generation because it can have better context of your code. Create a new chat inside CoPilot and enter something like this in it:

```json
Go through entire codebase and understand the code
The user wants to accomplish the following goal: <your goal>
Please analyze the workspace and generate one file in the workspace root called prd.json following the syntax below.
{
	"project": "TicTacToeCLI",
	"branchName": "ralph/tic-tac-toe-cli",
	"description": "Tic Tac Toe CLI Java Implementation broken down for atomic AI iterations",
	"userStories": [
		{
			"id": "US-001",
			"title": "Setup Project Structure and Enums",
			"description": "Setup Project Structure and Enums",
			"acceptanceCriteria": ["Setup Project Structure and Enums"],
			"priority": 1,
			"passes": true,
			"notes": ""
		}
	]
}
INSTRUCTIONS:
    - If the user forgot to provide a goal, ask him again to provide one. A goal is mandatory. If the provided goal is generic/placeholder/not clear enough. Ask again.
    - The json should have a logical sequence of steps organized into phases.
    - Each step should be granular enough to be independently executable and verifiable.
    - Number steps sequentially starting from "US-001".

IMPORTANT:
    - DO NOT use any absolute, user-specific, or local system-specific paths, directories, namespaces, or usernames in any command or file path.
    - All file paths and commands must be relative and portable, so the plan works for any user on any system.
    - Avoid referencing any local folders outside the workspace root.
    - Do not use commands that reference your own username, home directory, or machine-specific details.
    - The plan must be fully shareable and portable.
```

Put the above file at the root of your workspace, alongside `ralph.bat`.

### Set the model details

The main heart of the runner is the `ralph.bat` file. Before invoking it, make sure to set your custom model value in CMD. For me, I used an OpenAI-compatible model for reasoning and code generation. And the Deepseek-coder model for editing (finding diffs, etc). So, I used the following commands:

```bash
setx OPENAI_API_BASE <endpoint>
setx OPENAI_API_KEY <key>

setx OLLAMA_API_BASE http://127.0.0.1:11434
```

Note that you need to restart the shell after running the above commands.

### Run

Run `ralph.bat` once you are sure with all of the above items.
