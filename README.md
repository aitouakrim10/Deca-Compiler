# Compiler Usage

The `decac` command is used to compile Deca files.

## Syntax
```plaintext
decac [[-p | -v] [-n] [-r X] [-d]* [-P] [-w] <deca_file>...] | [-b]

# Compiler Usage

- **decac**: Display usage information.
- **-b (banner)**: Display a banner indicating the name of the team that made the project.
- **-p (parse)**: Stop decac after the tree construction step and display the decompilation of the tree. If only one source file is provided, the output must be a syntactically correct Deca program.
- **-v (verification)**: Stop decac after the verification step. No output is generated in the absence of errors.
- **-n (no check)**: Removes runtime tests specified in the Deca semantics remark.
- **-r X (registers)**: Limits available generalized registers to R0 ... R{X-1}, with 4 <= X <= 16.
- **-P (parallel)**: If there are multiple source files, launch the compilation of the files in parallel (to speed up compilation).
