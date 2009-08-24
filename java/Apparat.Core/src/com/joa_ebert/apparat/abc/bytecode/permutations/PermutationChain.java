/*
 * This file is part of Apparat.
 * 
 * Apparat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Apparat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Apparat. If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright (C) 2009 Joa Ebert
 * http://www.joa-ebert.com/
 * 
 */

package com.joa_ebert.apparat.abc.bytecode.permutations;

import com.joa_ebert.apparat.abc.AbcContext;
import com.joa_ebert.apparat.abc.AbcEnvironment;
import com.joa_ebert.apparat.abc.IMethodVisitor;
import com.joa_ebert.apparat.abc.Method;

/**
 * 
 * @author Joa Ebert
 * 
 */
public class PermutationChain implements IMethodVisitor
{
	private final IBytecodePermutation[] permutations;

	public PermutationChain( final IBytecodePermutation[] permutations )
	{
		this.permutations = permutations;
	}

	public void visit( final AbcContext context, final Method method )
	{
		final AbcEnvironment environment = new AbcEnvironment( context );

		if( null == method || null == method.body || null == method.body.code )
		{
			return;
		}

		for( final IBytecodePermutation permutation : permutations )
		{
			do
			{
				permutation.interpret( environment, method.body.code );
			}
			while( permutation.modified() );
		}
	}
}